import React from 'react';
import classnames from "classnames";

const Input = (props) => {
    const { error, name, onChange, placeholder, value, type, disabled} = props;

    return (
        <div className="form-group mb-3">
            <label htmlFor={name}>{placeholder}</label>
            <input
                   className={classnames("form-control form-control-lg", {
                       "is-invalid": error
                   })}
                   name={name}
                   value={value}
                   type={type}
                   onChange={onChange}
                   disabled={disabled}/>
            {error && <div className="invalid-feedback">{error}</div>}
        </div>
    );
};

export default Input;