import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Rules } from "../../Components/Rules/Rules";

export const UserHomePage: React.FC = () => {
    const navigator = useNavigate();
    const [showRules, setShowRules] = useState(false);

    const handleShowRules = (event: React.MouseEvent<HTMLButtonElement>) => {
        setShowRules(!showRules);
    }

    return (
        <>
            <div className="user-home-page">
                {showRules ? <button className="rules-btn" onClick={handleShowRules}></button> : <Rules />}
            </div>
        </>
    )
}