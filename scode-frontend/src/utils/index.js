import { trim, includes } from 'ramda';

export function trimData(data, fields) {
    // Find out which field should be trim
    // - all data will be trim by default when fields is missing
    fields = fields ?? Object.keys(data);

    for(let name in data) {
        if (includes(name, fields)) {
            data[name].value = trim(data[name].value);
        }
    }

    return data;
}
