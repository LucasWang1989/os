export async function fetchReceipt({ orderNo } = {}) {
    const url = `/api/customer/checkout/orders/${orderNo.toString() ? `${orderNo}` : ""}`;

    const res = await fetch(url, { method: "GET" });
    if (!res.ok) throw new Error("Failed to fetch receipt");
    return res.json(); // { orderProducts }
}
