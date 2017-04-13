package com.thegame.server.common.reflection;

import com.thegame.server.common.logging.LogStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * @author afarre
 */
public class PackageUtils {
 
    private static final LogStream log=LogStream.getLogger(PackageUtils.class);
    
    protected static final String JRE_LIBRARIES[] = {"/jre/lib/","/jdk/lib/","/lib/rt.jar"};

    
    /**
     * Recover the current classloaders from context (thread classloader) and from static (class classloader) appending to the given ones if received any
     * @param _classLoaders Additional classloaders to use
     * @return An unique list of all available classloaders.
     */
    public static final List<ClassLoader> getClassLoaders(final ClassLoader... _classLoaders){
        
        final List<ClassLoader> reply=new ArrayList<>(2);

        log.trace("recover-classloaders::begin");
        if(_classLoaders!=null){
            Arrays.stream(_classLoaders)
                    .distinct()
                    .peek(classLoader -> log.finest("recover-classloaders::adding-requested-classloader::{}",classLoader.getClass().getName()))
                    .forEach(classLoader -> reply.add(classLoader));
        }
        final ClassLoader contextClassLoader=Thread.currentThread().getContextClassLoader();
        if((contextClassLoader!=null)&&(!reply.contains(contextClassLoader))){
            log.debug("recover-classloaders::adding-context-classloader::{}",contextClassLoader.getClass().getName());
            reply.add(contextClassLoader);
        }
        final ClassLoader staticClassLoader=PackageUtils.class.getClassLoader();
        if((staticClassLoader!=null)&&(!reply.contains(staticClassLoader))){
            log.debug("recover-classloaders::adding-static-classloader::{}",staticClassLoader.getClass().getName());
            reply.add(staticClassLoader);
        }
        log.trace("recover-classloaders::end");
        
        return reply;
    }
    
    /**
     * Check if this url is one of the JRE classpath resources using; to check it
     * compares with the JRE_LIBRARIES static attribute.
     * @param _url url to check
     * @return true if is a JRE resource
     */
    public static final boolean isJREClasspathResource(final URL _url){
        return Arrays.stream(JRE_LIBRARIES)
                .anyMatch(library -> _url.toString().contains(library));
    }
    
    private static List<String> patternConverter(final List<String> _stringPatterns){
        
        final List<String> reply;
    
        if(_stringPatterns!=null){
            reply=_stringPatterns.stream()
                    .map((stringPattern) -> stringPattern.replaceAll("\\.","\\."))
                    .map((stringPattern) -> stringPattern.replaceAll("\\*",".*"))
                    .collect(Collectors.toList());
        }else{
            reply=Collections.emptyList();
        }
        
        return reply;
    } 
    private static boolean isAcceptedClass(final String _class,final List<String> _basePackages,final List<String> _filters){
        return Optional.of(_class)
                .filter(clazz -> _basePackages.stream().anyMatch(basePackage -> clazz.startsWith(basePackage)))
                .filter(clazz -> _filters.stream().anyMatch(filter -> clazz.matches(filter)))
                .isPresent();
    } 
    
    /**
     * Search for resources in classpath existing inside the given basepackages and accomplishing at least one of the given filters
     * @param _basePackage List of base packages to check
     * @param _filters List of filters to validate
     * @param _classLoaders Classloaders to use (optional)
     * @return List of the urls of all classpath resources existing inside the given basepackages and accomplishing at least one of the given filters
     * @throws IOException
     */
    public static final Set<String> getExistentResources(final List<String> _basePackage,final List<String> _filters,final ClassLoader... _classLoaders) throws IOException{
        return getExistentResourcesFromPattern(_basePackage, patternConverter(_filters), _classLoaders);
    }

    /**
     * Search for resources in classpath existing inside the given basepackages and accomplishing at least one of the given regular expression pattern filters
     * @param _basePackage List of base packages to check
     * @param _patternFilters List of pattern filters to validate
     * @param _classLoaders Classloaders to use (optional)
     * @return List of the urls of all classpath resources existing inside the given basepackages and accomplishing at least one of the given regex pattern filters
     * @throws IOException
     */
    public static final Set<String> getExistentResourcesFromPattern(final List<String> _basePackage,final List<String> _patternFilters,final ClassLoader... _classLoaders) throws IOException{
        
        final Set<String> reply=new HashSet<>();    

        log.trace("find-packages::from::{}::begin",_basePackage);
        final List<ClassLoader> classloaders=getClassLoaders(_classLoaders);
        Set<String> classpathEntries=classloaders.stream()
                .flatMap(p -> Arrays.asList(((URLClassLoader)p).getURLs()).stream())
                .map(p -> p.getFile())
                .collect(Collectors.toCollection(HashSet::new));
        log.finest("find-packages::from::{}::classloaders-classpath-entries::{}",_basePackage,classpathEntries);
        final String pathSep=System.getProperty("path.separator");
        final String classpath=System.getProperty("java.class.path");
        final String[] systemClasspathEntries = classpath.split(pathSep);
        log.finest("find-packages::from::{}::system-classpath-entries::{}",_basePackage,systemClasspathEntries);
        classpathEntries.addAll(Arrays.asList(systemClasspathEntries));
        log.finest("find-packages::from::{}::classpath-entries::{}",_basePackage,classpathEntries);
        for(String classPathResource:classpathEntries){
            final URL url = new File(classPathResource).toURI().toURL();
            log.trace("find-packages::from::{}::processing::{}",_basePackage,url.toString());
            if(!isJREClasspathResource(url)){
                log.finest("find-packages::from::{}::processing::{}::accepted",_basePackage,url.toString());
                final File file=new File(url.getFile());
                if(file.isFile()){
                    reply.addAll(PackageUtils.getExistentResourcesFromFileFilteredByPattern(file,_basePackage,_patternFilters,classloaders));
                }else{
                    reply.addAll(PackageUtils.getExistentResourcesFromFolderFilteredByPattern(file,_basePackage,_patternFilters,classloaders));
                }
            }else{
                log.finest("find-packages::from::{}::processing::{}::discarted",_basePackage,url.toString());
            }
        }
        log.debug("find-packages::from::{}::end",_basePackage);
                
        return reply;
    }

    /**
     * Search for resources existing in the specified jar file inside the given basepackages and accomplishing at least one of the given filters
     * @param _file jar file to scan
     * @param _basePackage List of base packages to check
     * @param _filters List of filters to validate
     * @param _classLoaders Classloaders to use (optional)
     * @return List of the urls of resources existing inside the given basepackages and accomplishing at least one of the given filters
     * @throws IOException
     */
    public static final Set<String> getExistentResourcesFromFile(final File _file, final List<String> _basePackage,final List<String> _filters, final ClassLoader... _classLoaders) throws IOException{
        return getExistentResourcesFromFileFilteredByPattern(_file, _basePackage, patternConverter(_filters), _classLoaders);
    }
    /**
     * Search for resources existing in the specified jar file inside the given basepackages and accomplishing at least one of the given regular expression pattern filters
     * @param _file jar file to scan
     * @param _basePackage List of base packages to check
     * @param _patternFilters List of pattern filters to validate
     * @param _classLoaders Classloaders to use (optional)
     * @return List of the urls of resources existing inside the given basepackages and accomplishing at least one of the given regular expression pattern filters
     * @throws IOException
     */
    public static final Set<String> getExistentResourcesFromFileFilteredByPattern(final File _file, final List<String> _basePackage,final List<String> _patternFilters, final ClassLoader... _classLoaders) throws IOException{
        final List<ClassLoader> classloaders=getClassLoaders(_classLoaders);
        return PackageUtils.getExistentResourcesFromFileFilteredByPattern(_file,_basePackage,_patternFilters,classloaders);
    }
    /**
     * Search for resources existing in the specified jar file inside the given basepackages and accomplishing at least one of the given filters
     * @param _file jar file to scan
     * @param _basePackage List of base packages to check
     * @param _filters List of filters to validate
     * @param _classLoaders List of classloaders to use
     * @return List of the urls of resources existing inside the given basepackages and accomplishing at least one of the given filters
     * @throws IOException
     */
    public static final Set<String> getExistentResourcesFromFile(final File _file, final List<String> _basePackage,final List<String> _filters, final List<ClassLoader> _classLoaders) throws IOException{
        return getExistentResourcesFromFileFilteredByPattern(_file, _basePackage, patternConverter(_filters), _classLoaders);
    }
    /**
     * Search for resources existing in the specified jar file inside the given basepackages and accomplishing at least one of the given regular expression pattern filters
     * @param _file jar file to scan
     * @param _basePackage List of base packages to check
     * @param _patternFilters List of pattern filters to validate
     * @param _classLoaders List of classloaders to use
     * @return List of the urls of resources existing inside the given basepackages and accomplishing at least one of the given regular expression pattern filters
     * @throws IOException
     */
    public static final Set<String> getExistentResourcesFromFileFilteredByPattern(final File _file, final List<String> _basePackage,final List<String> _patternFilters, final List<ClassLoader> _classLoaders) throws IOException{
        
        final Set<String> reply=new HashSet<>();    

        log.trace("find-packages::from::{}::at-file::{}::begin",_basePackage,_file);
        final JarFile jarFile=new JarFile(_file);
        final Enumeration<JarEntry> entries=jarFile.entries();
        while(entries.hasMoreElements()){
            final JarEntry entry=entries.nextElement();
            log.finest("find-packages::from::{}::at-file::{}::entry::{}",_basePackage,_file,entry.getName());
            if(!entry.isDirectory()){
                log.finest("find-packages::from::{}::at-file::{}::entry::{}::file::processing",_basePackage,_file,entry.getName());
                final String entryClass=entry.getName().replace('/','.').trim();
                if(isAcceptedClass(entryClass,_basePackage,_patternFilters)){
                    log.debug("find-packages::from::{}::at-file::{}::entry::{}::class::{}::accepted",_basePackage,_file,entry.getName(),entryClass);
                    reply.add(entryClass);
                }
            }else{
                log.trace("find-packages::from::{}::at-file::{}::entry::{}::folder::discarded",_basePackage,_file,entry.getName());
            }
        }
        log.debug("find-packages::from::{}::at-file::{}::end",_basePackage,_file);
        
        return reply;
    }

    
    /**
     * Search for resources existing in the specified folder inside the given basepackages and accomplishing at least one of the given filters
     * @param _file jar file to scan
     * @param _basePackage List of base packages to check
     * @param _filters List of filters to validate
     * @param _classLoaders Classloaders to use (optional)
     * @return List of the urls of resources existing inside the given basepackages and accomplishing at least one of the given filters
     * @throws IOException
     */
    public static final Set<String> getExistentResourcesFromFolder(final File _file, final List<String> _basePackage,final List<String> _filters,final ClassLoader... _classLoaders) throws IOException{
        return getExistentResourcesFromFolderFilteredByPattern(_file, _basePackage, patternConverter(_filters), _classLoaders);
    }
    /**
     * Search for resources existing in the specified folder inside the given basepackages and accomplishing at least one of the given regular expression pattern filters
     * @param _file jar file to scan
     * @param _basePackage List of base packages to check
     * @param _patternFilters List of pattern filters to validate
     * @param _classLoaders Classloaders to use (optional)
     * @return List of the urls of resources existing inside the given basepackages and accomplishing at least one of the given regular expression pattern filters
     * @throws IOException
     */
    public static final Set<String> getExistentResourcesFromFolderFilteredByPattern(final File _file, final List<String> _basePackage,final List<String> _patternFilters,final ClassLoader... _classLoaders) throws IOException{
        final List<ClassLoader> classloaders=getClassLoaders(_classLoaders);
        return PackageUtils.getExistentResourcesFromFolderFilteredByPattern(_file,_basePackage,_patternFilters,classloaders);
    }
    /**
     * Search for resources existing in the specified folder inside the given basepackages and accomplishing at least one of the given filters
     * @param _file jar file to scan
     * @param _basePackage List of base packages to check
     * @param _filters List of filters to validate
     * @param _classLoaders List of classloaders to use
     * @return List of the urls of resources existing inside the given basepackages and accomplishing at least one of the given filters
     * @throws IOException
     */
    public static final Set<String> getExistentResourcesFromFolder(final File _file, final List<String> _basePackage,final List<String> _filters, final List<ClassLoader> _classLoaders) throws IOException{
        return getExistentResourcesFromFolderFilteredByPattern(_file, _basePackage, patternConverter(_filters), _classLoaders);
    }
    /**
     * Search for resources existing in the specified folder inside the given basepackages and accomplishing at least one of the given regular expression pattern filters
     * @param _file jar file to scan
     * @param _basePackage List of base packages to check
     * @param _patternFilters List of pattern filters to validate
     * @param _classLoaders List of classloaders to use
     * @return List of the urls of resources existing inside the given basepackages and accomplishing at least one of the given regular expression pattern filters
     * @throws IOException
     */
    public static final Set<String> getExistentResourcesFromFolderFilteredByPattern(final File _file, final List<String> _basePackage,final List<String> _patternFilters, final List<ClassLoader> _classLoaders) throws IOException{
        
        final Set<String> reply=new HashSet<>();    
        final Stack<File> fileStack=new Stack<>();
        final int baseFileLength=(int)(_file.getAbsolutePath()).length()+1;
        
        log.trace("find-packages::from::{}::at-folder::{}::begin",_basePackage,_file);
        fileStack.add(_file);
        while(!fileStack.isEmpty()){
            final File currentFile=fileStack.pop();
            if(currentFile.isDirectory()){
                log.finest("find-packages::from::{}::at-folder::{}::file-folder::{}::analyze",_basePackage,_file,currentFile.getName());
                fileStack.addAll(Arrays.asList(currentFile.listFiles()));
            }else{
                log.finest("find-packages::from::{}::at-folder::{}::file-folder::{}::process",_basePackage,_file,currentFile.getName());
                final String packagePath=currentFile.getAbsolutePath().substring(baseFileLength);
                final String entryClass=packagePath.replace(File.separatorChar,'.').trim();
                if(isAcceptedClass(entryClass,_basePackage,_patternFilters)){
                    log.debug("find-packages::from::{}::at-folder::{}::file::{}::class::{}::accepted",_basePackage,_file,currentFile.getName(),entryClass);
                    reply.add(entryClass);
                }
            }
        }
        log.debug("find-packages::from::{}::at-folder::{}::end",_basePackage,_file);        
        
        return reply;
    }
}
