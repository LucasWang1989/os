import React from "react";

export default function CartHeader({ tableNo }) {
    return (
        <div className="take-delivery">
            <div className="addr">
                <p style={{ color: "#FA2C2A" }} className="ng-binding" id="tableno">
                    Table No: {tableNo || ""}
                </p>
            </div>
        </div>
    );
}
