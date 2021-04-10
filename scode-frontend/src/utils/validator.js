import * as R from 'ramda';

const validators = {

    isNotEmpty(field, fieldName) {
        if (R.isEmpty(field.value)) {
            alert(`Please enter ${fieldName}`);
            field.ref.focus();
            return false;
        }

        return true;
    },

    isEmail(field, fieldName) {
        if (!/\S+@\S+\.\S+/.test(field.value)) {
            alert(`Invalid email format invalid`);
            field.ref.focus();
            return false;
        }

        return true;
    },

    isLengthBiggerThan(field, fieldName, maxLength) {
        if (field.value.length < maxLength) {
            alert(`The ${fieldName} must more than ${maxLength} letters`);
            field.ref.focus();
            return false;
        }

        return true;
    },

}

const Validator = {

    validate(data, rules) {
        for(let [fieldName, rule] of Object.entries(rules)) {
            for(let validator of rule.split('|')) {
                let [validatorName, validatorArguments] = validator.split(':');

                // Ensure arguments is array not undefined
                validatorArguments = validatorArguments?.split(',') ?? [];

                // Stop the validation when current rule failed
                const result = validators[validatorName].call(
                    this, data[fieldName], fieldName, ...validatorArguments
                );

                if (!result) {
                    return false;
                }
            }
        }

        return true;
    },

    passes(data, rules) {
        return this.validate(data, rules);
    },

    fail(data, rules) {
        return !this.passes(data, rules);
    },

}

export default Validator;
