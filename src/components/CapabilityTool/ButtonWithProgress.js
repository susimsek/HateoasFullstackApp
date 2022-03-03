import React from 'react';
import {Button, Spinner} from "react-bootstrap";

const ButtonWithProgress = (props) => {
    const { onClick, type, size, variant, pendingApiCall, children, disabled, className } = props;
    return (
        <Button disabled={disabled}
                type={type}
                variant={variant}
                size={size}
                className={className}
                onClick={onClick}>
            {pendingApiCall && <Spinner
                as="span"
                animation="border"
                size="sm"
                role="status"
                aria-hidden="true">
                <span className="visually-hidden">Loading...</span>
            </Spinner>} {children}
        </Button>
    );
};

export default ButtonWithProgress;