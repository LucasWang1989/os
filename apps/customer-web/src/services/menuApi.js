export async function fetchMenu(tableNo) {
    const res = await fetch(`/api/customer/menus?tableNo=${encodeURIComponent(tableNo || "")}`);
    if (!res.ok) throw new Error("Failed to load menu");
    return res.json(); // { tableNo, products: [...] }
}
