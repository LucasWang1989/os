import React from "react";
import ProductItem from "./ProductItem";

export default function ProductList({ products, cart, onAdd, onRemove }) {
    return (
        <ul className="lists lists-tof ng-scope">
            {products.map((p) => (
                <ProductItem
                    key={p.id}
                    product={p}
                    qty={cart[p.id]?.qty || 0}
                    onAdd={onAdd}
                    onRemove={onRemove}
                />
            ))}
        </ul>
    );
}
