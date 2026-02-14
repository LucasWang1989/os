import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { loadTable } from "../services/cartStorage";

export default function CartEmptyPage() {
    const navigate = useNavigate();
    const [tableNo, setTableNo] = useState("");

    useEffect(() => {
        const table = loadTable(); // { id: "1" }
        setTableNo(table?.id || "");
    }, []);

    const goToOrder = (e) => {
        e.preventDefault();

        if (tableNo) navigate(`/menu?tableNo=${encodeURIComponent(tableNo)}`);
        else navigate("/menu");
    };

    return (
        <div className="ng-scope">
            <div className="cartempty ng-scope">
                <h3>The shopping cart is empty</h3>
                <a id="go2order" onClick={goToOrder}>
                    Go to Order
                </a>
            </div>
        </div>
    );
}
