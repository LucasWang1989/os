import React from "react";

export default function ReceiptTotals({ totalMoney }) {
    return (
        <div className="bottom">
            <p>Total: ${totalMoney}</p>
            <p className="money">Actually Paid: ${totalMoney}</p>
        </div>
    );
}
