import React from "react";
import CartItemRow from "./CartItemRow";

export default function CartItemList({ lines, onAdd, onRemove }) {
    return (
        <ul id="prolist" className="lists">
            {lines.map(({ product, qty }) => (
                <CartItemRow
                    key={product.id}
                    product={product}
                    qty={qty}
                    onAdd={() => onAdd(product.id, product)}
                    onRemove={() => onRemove(product.id)}
                />
            ))}
        </ul>
    );
}
