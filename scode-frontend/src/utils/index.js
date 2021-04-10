import { isEmpty, trim } from 'ramda';

export function isEmail(email) {
    return /\S+@\S+\.\S+/.test(email);
}

export function checkIsEmpty(field, message) {
    if (isEmpty(field.value)) {
        alert(message);
        field.ref.focus();
        return false;
    }

    return true;
}

export function checkIsEmail(field, message) {
    if (!isEmail(field.value)) {
        alert(message);
        field.ref.focus();
        return false;
    }

    return true;
}

export function checkIsLengthBigger(field, message, minLength) {
    if (field.value.length < minLength) {
        alert(message);
        field.ref.focus();
        return false;
    }

    return true;
}

export function checkIsValidate(fields) {
    for(let field of fields) {
        for(let rule of field.rules) {
            if (!rule.name.call(this, field.control, rule.message, ...rule.args)) {
                return false;
            }
        }
    }

    return true;
}
