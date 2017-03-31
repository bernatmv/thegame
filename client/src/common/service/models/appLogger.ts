enum LogLevel {
    LOG = 1,
    DEBUG,
    ERROR
}

const style = {
    log: [
        'background: #ddd',
        'font-weight: 800',
        'color: black'
    ].join('; '),
    error: [
        'background: yellow',
        'font-weight: 800',
        'color: red'
    ].join('; '),
    debug: [
        'background: #11a9e2',
        'font-weight: 800',
        'color: white'
    ].join('; ')
};

function _print(logLevel: LogLevel, ...payload): void {
    if (logLevel === LogLevel.LOG) {
        //TODO: add log to Bunyan
        console.log(`%c[LOG]`, style.log, ...payload);//tslint:disable-line
    } else if (logLevel === LogLevel.DEBUG) {
        console.debug(`%c[DEBUG]`, style.debug, ...payload);//tslint:disable-line
    } else if (logLevel === LogLevel.ERROR) {
        console.error(`%c[ERROR]`, style.error, ...payload);//tslint:disable-line
    }
}

export function log(...payload):void {
    _print(LogLevel.LOG, ...payload);
}

export function debug(...payload):void {
    _print(LogLevel.DEBUG, ...payload);
}