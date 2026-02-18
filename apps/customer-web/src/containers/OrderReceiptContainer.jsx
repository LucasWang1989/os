import React, { useEffect, useMemo, useState } from "react";
import { fetchReceipt } from "../services/receiptApi";
import ReceiptHeader from "../components/receipt/ReceiptHeader";
import ReceiptCard from "../components/receipt/ReceiptCard";
import {formatDateTime} from "../common/datetools.js";

export default function OrderReceiptContainer() {
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [orderProducts, setOrderProducts] = useState([]);

    useEffect(() => {
        const qs = new URLSearchParams(window.location.search);
        const orderNo = qs.get("orderNo") || "";
        // const tableNo = qs.get("tableNo") || "";
        // const token = qs.get("token") || "";

        setLoading(true);
        setError("");

        fetchReceipt({ orderNo })
            .then((data) => {
                setOrderProducts(Array.isArray(data?.orderProducts) ? data.orderProducts : []);
            })
            .catch((e) => {
                console.error(e);
                setError("Failed to load your receipt.");
                setOrderProducts([]);
            })
            .finally(() => setLoading(false));
    }, []);

    const receipt = useMemo(() => {
        const first = orderProducts[0] || null;

        const tableNo = first?.tableNo || "";
        const orderNo = first?.orderNo || "";

        let totalCents = 0;
        for (const p of orderProducts) {
            const price = Number(p.price || 0);
            const qty = Number(p.dishNumber || 0);
            totalCents += price * qty;
        }

        const status = first?.status || "PAID";

        let createdAtText = first?.orderCreatedDate + first?.orderCreatedTime;
        if(createdAtText) createdAtText = formatDateTime(createdAtText);

        return {
            tableNo,
            orderNo,
            status,
            createdAtText,
            totalMoney: (totalCents / 100).toFixed(2),
        };
    }, [orderProducts]);

    return (
        <div className="ng-scope">
            <ReceiptHeader />

            <div className="orderlist" id="order_lists">
                {loading && <div style={{ padding: 12 }}>Loading...</div>}

                {!loading && error && <div style={{ padding: 12 }}>{error}</div>}

                {!loading && !error && (
                    <ReceiptCard
                        orderProducts={orderProducts}
                        receipt={receipt}
                    />
                )}
            </div>
        </div>
    );
}
