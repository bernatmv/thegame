/** Global definitions for developement **/

// for style loader
declare module '*.css' {
  const styles: any;
  export = styles;
}

declare module '*.png' {
  const image: any;
  export = image;
}

// for redux devtools extension
declare interface Window {
  devToolsExtension?(): (args?: any) => any;
}