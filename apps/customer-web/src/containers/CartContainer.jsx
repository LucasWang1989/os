import React, { useEffect, useMemo, useState } from "react";
import useCart from "../hooks/useCart";
import {loadTable} from "../services/cartStorage";
import CartHeader from "../components/cart/CartHeader";
import CartItemList from "../components/cart/CartItemList";
import CartCheckoutBar from "../components/cart/CartCheckoutBar";
import {checkout} from "../services/checkoutApi.js";

export default function CartContainer() {
    const { cart, add, remove, totalCount } = useCart();
    const [tableNo, setTableNo] = useState("");

    useEffect(() => {
        const table = loadTable();
        const tn = table?.id || "";
        setTableNo(tn);
    }, []);

    const cartLines = useMemo(() => {
        return Object.values(cart || {})
            .map((item) => ({
                product: item,
                qty: item.qty
            }))
            .filter((x) => x.qty > 0);
    }, [cart]);


    const totalMoney = useMemo(() => {
        const cents = Object.values(cart || {})
            .reduce((sum, item) => sum + item.price * item.qty, 0);

        return (cents / 100).toFixed(2);
    }, [cart]);


    const handleCheckout = async () => {
        try {
            const data = await checkout(tableNo, cartLines);
            window.location.replace(data.payUrl);
        } catch (e) {
            console.error(e);
            alert("Checkout failed. Please try again.");
        }
    };

    return (
        <div className="ng-scope">
            <div className="cartpay ng-scope">
                <CartHeader tableNo={tableNo} />

                <section className="prolist_section">
                    <CartItemList
                        lines={cartLines}
                        onAdd={add}
                        onRemove={remove}
                    />
                </section>

                <CartCheckoutBar
                    totalMoney={totalMoney}
                    disabled={totalCount === 0}
                    onCheckout={handleCheckout}
                />
            </div>
        </div>
    );
}
