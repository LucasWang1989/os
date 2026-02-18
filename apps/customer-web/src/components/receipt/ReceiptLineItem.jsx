import React from "react";

function resolveImage(path) {
    if (!path) return "";
    if (path.startsWith("http://") || path.startsWith("https://")) return path;
    if (path.startsWith("img/") || path.startsWith("images/")) return `/assets/${path}`;
}

export default function ReceiptLineItem({ item }) {
    const priceDollars = (Number(item.price || 0) / 100).toFixed(2);
    const qty = Number(item.dishNumber || 0);

    return (
        <li
            style={{
                display: "flex",
                alignItems: "center",
                padding: "20px 0"
            }}
        >
            <div
                className="l"
                style={{
                    flex: "0 0 120px",
                    textAlign: "center"
                }}
            >
                <img
                    src={resolveImage(item.imagePath)}
                    className="img-responsive"
                    alt="..."
                    width="87"
                    height="87"
                />
            </div>

            <div
                style={{
                    flex: 1,
                    textAlign: "center"
                }}
            >
                <p className="t" style={{ marginBottom: "6px" }}>
                    {item.name}
                </p>
                <p className="money" style={{ color: "#FA2C2A" }}>
                    ${priceDollars} x {qty}
                </p>
            </div>

            <div
                style={{
                    flex: "0 0 120px"
                }}
            />
        </li>
    );


}
