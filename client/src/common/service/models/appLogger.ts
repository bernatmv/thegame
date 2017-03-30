enum LogLevel {
    LOG = 1,
    DEBUG
}

function _print(logLevel: LogLevel, ...payload): void {
    if (logLevel === LogLevel.LOG) {
        //TODO: add log to Bunyan
        console.log(`[${logLevel.toString()}] `, ...payload);//tslint:disable-line
    } else if (logLevel === LogLevel.DEBUG) {
        console.debug(`[${logLevel.toString()}] `, ...payload);//tslint:disable-line
    }
}

export function log(...payload):void {
    _print(LogLevel.LOG, ...payload);
}

export function debug(...payload):void {
    _print(LogLevel.DEBUG, ...payload);
}