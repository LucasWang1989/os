import React, { useEffect, useMemo, useState } from "react";
import TableInfo from "../components/menu/TableInfo";
import Banner from "../components/menu/Banner";
import ProductList from "../components/menu/ProductList";
import CartBar from "../components/menu/CartBar";
import useCart from "../hooks/useCart";
import { fetchMenu } from "../services/menuApi";
import { clearSession, saveTable } from "../services/cartStorage";
import { useNavigate } from "react-router-dom";

export default function MenuContainer() {
    const navigate = useNavigate();

    const [tableNo, setTableNo] = useState("");
    const [products, setProducts] = useState([]);

    const { cart, add, remove, totalCount, clear } = useCart();

    useEffect(() => {
        clearSession();
        clear();

        const tn = new URLSearchParams(window.location.search).get("tableNo") || "";
        setTableNo(tn);

        fetchMenu(tn)
            .then((data) => {
                setTableNo(data.tableNo ?? tn);
                setProducts(Array.isArray(data.products) ? data.products : []);
            })
            .catch((e) => {
                console.error(e);
                setProducts([]);
            });
    }, []);

    const totalMoney = useMemo(() => {
        let cents = 0;
        for (const p of products) {
            const qty = cart[p.id]?.qty || 0;
            cents += (p.price || 0) * qty;
        }
        return (cents / 100).toFixed(2);
    }, [cart, products]);

    const toCart = () => {
        // Set table number whether products exist.
        saveTable(tableNo);

        if (totalCount === 0) {
            //window.location.href = "pages/cart_empty.html";
            navigate("/cart/empty");
        } else {
            navigate("/cart");
        }
    };

    return (
        <div className="ng-scope" style={{ position: "relative" }}>
            <TableInfo tableNo={tableNo} />
            <div className="ng-scope">
                <Banner src="assets/img/tof/timg-6.jpg" />

            </div>

            <ProductList
                products={products}
                cart={cart}
                onAdd={add}
                onRemove={remove}
            />

            <CartBar totalMoney={totalMoney} totalCount={totalCount} onConfirm={toCart} />
        </div>
    );
}
