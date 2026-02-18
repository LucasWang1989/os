const KEY_TABLE = "table";
const KEY_CART = "cart";

export function clearSession() {
    sessionStorage.clear();
}

export function saveTable(tableNo) {
    sessionStorage.setItem(KEY_TABLE, JSON.stringify({ id: tableNo }));
}

export function saveCart(cartObj) {
    // cartObj: { [productId]: qty }
    sessionStorage.setItem(KEY_CART, JSON.stringify(cartObj || {}));
}

export function loadCart() {
    try {
        const raw = sessionStorage.getItem(KEY_CART);
        return raw ? JSON.parse(raw) : {};
    } catch {
        return {};
    }
}

export function loadTable() {
    try {
        const raw = sessionStorage.getItem(KEY_TABLE);
        return raw ? JSON.parse(raw) : {};
    } catch {
        return {};
    }
}
