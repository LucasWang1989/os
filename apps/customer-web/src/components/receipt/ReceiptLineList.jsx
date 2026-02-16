import React from "react";
import ReceiptLineItem from "./ReceiptLineItem";

export default function ReceiptLineList({ orderProducts }) {
    return (
        <ul className="pdtlist">
            {orderProducts.map((p, idx) => (
                <ReceiptLineItem key={p.id || `${p.orderNo}-${idx}`} item={p} />
            ))}
        </ul>
    );
}
