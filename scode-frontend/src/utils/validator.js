import * as R from 'ramda';

export function isEmpty(field, message) {
    if (R.isEmpty(field.value)) {
        alert(message);
        field.ref.focus();
        return false;
    }

    return true;
}

export function isEmail(field, message) {
    if (!/\S+@\S+\.\S+/.test(field.value)) {
        alert(message);
        field.ref.focus();
        return false;
    }

    return true;
}

export function isLengthBigger(field, message, minLength) {
    if (field.value.length < minLength) {
        alert(message);
        field.ref.focus();
        return false;
    }

    return true;
}

export function isValidated(fields) {
    for(let field of fields) {
        for(let rule of field.rules) {
            if (!rule.name.call(this, field.control, rule.message, ...rule.args)) {
                return false;
            }
        }
    }

    return true;
}
