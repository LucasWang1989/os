import React from "react";
import ReceiptLineList from "./ReceiptLineList";
import ReceiptTotals from "./ReceiptTotals";

export default function ReceiptCard({ orderProducts, receipt }) {
    return (
        <ul className="lists ng-scope">
            <li>
                <p>
                    {receipt.createdAtText ? receipt.createdAtText + " " : ""}
                    <span className="status">{receipt.status}</span>
                </p>

                <p>Order No: {receipt.orderNo}</p>
                <p>Table No: {receipt.tableNo}</p>

                <ReceiptLineList orderProducts={orderProducts} />
                <ReceiptTotals totalMoney={receipt.totalMoney} />
            </li>
        </ul>
    );
}
