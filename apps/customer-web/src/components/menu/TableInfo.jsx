import React from "react";

export default function TableInfo({ tableNo }) {
    return (
        <div className="loca ng-scope">
            <a
                id="tableNo"
                className="change ng-binding"
                href="/"
                tableno={tableNo}
                onClick={(e) => e.preventDefault()}
            >
                Table No: {tableNo}
            </a>
        </div>
    );
}
