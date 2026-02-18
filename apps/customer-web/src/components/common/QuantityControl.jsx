import React from "react";

export default function QuantityControl({ value, onAdd, onRemove }) {
    return (
        <div className="num_con">
            <img
                src="assets/img/num_l.png"
                className="img-responsive num_l"
                alt="..."
                onClick={onRemove}
                style={{ cursor: "pointer" }}
            />
            <span className="number">{value}</span>
            <img
                src="assets/img/num_r.png"
                className="img-responsive num_r"
                alt="..."
                onClick={onAdd}
                style={{ cursor: "pointer" }}
            />
        </div>
    );
}
