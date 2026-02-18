export async function checkout(tableNo, cartLines) {
    const payload = {
        tableNo,
        items: cartLines.map((x) => ({
            id: x.product.id,
            amount: x.qty,
            name: x.product.name,
            price: x.product.price,
            imagePath: x.product.imagePath,
        })),
    };

    const res = await fetch("/api/customer/checkout/orders", {
        method: "POST",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        body: JSON.stringify(payload),
    });

    if (!res.ok) throw new Error("Checkout failed");

    return await res.json();
}
