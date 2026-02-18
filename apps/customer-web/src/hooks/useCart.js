import { useEffect, useMemo, useState } from "react";
import { loadCart, saveCart } from "../services/cartStorage";

export default function useCart() {
    const [cart, setCart] = useState(() => {
        return loadCart() || {};
    });

    useEffect(() => {
        saveCart(cart);
    }, [cart]);

    const add = (productId, product) => {
        setCart((prev) => {
            const existing = prev[productId];

            if (existing) {
                // existing in cart → qty +1
                return {
                    ...prev,
                    [productId]: {
                        ...existing,
                        qty: existing.qty + 1,
                    },
                };
            }

            // First time → put entire dish in + qty=1
            return {
                ...prev,
                [productId]: {
                    ...product,
                    qty: 1,
                },
            };
        });
    };

    const remove = (productId) => {
        setCart((prev) => {
            const existing = prev[productId];
            if (!existing) return prev;

            if (existing.qty <= 1) {
                // Qty 0 → delete product
                const copy = { ...prev };
                delete copy[productId];
                return copy;
            }

            return {
                ...prev,
                [productId]: {
                    ...existing,
                    qty: existing.qty - 1,
                },
            };
        });
    };

    const totalCount = useMemo(
        () => Object.values(cart).reduce((sum, p) => sum + (p?.qty || 0), 0),
        [cart]
    );

    const clear = () => setCart({});

    return { cart, add, remove, totalCount, clear };
}
