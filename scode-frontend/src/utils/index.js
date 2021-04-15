import { trim, includes } from 'lodash';

export function trimData(data, fields) {
    // Find out which field should be trim
    // - all data will be trim by default when fields is missing
    fields = fields ?? Object.keys(data);

    for(let name in data) {
        if (includes(fields, name)) {
            data[name].value = trim(data[name].value);
        }
    }

    return data;
}

export function formatDate(date) {
    const now = new Date(date);

    const year    = now.getFullYear();
    const month   = now.getMonth() + 1;
    const day     = now.getDay();
    const hours   = now.getHours();
    const minutes = now.getMinutes();
    const seconds = now.getSeconds();

    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}
