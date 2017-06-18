export let nullable = (value: any) => {
    if (value === undefined) {
        return null;
    }
    return value;
};