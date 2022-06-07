import React, { useState } from "react";

export const Rules:React.FC = () => {

    const [showRules, setShowRules] = useState(false);

    const handleShowRules = (event:React.MouseEvent<HTMLDivElement> | React.MouseEvent<HTMLButtonElement>) => {
        setShowRules(!showRules);
    }

    return (
        <div className="rules-container" onClick={handleShowRules}>
            
            { showRules ? 
          <div className="rules-text">
              
              <h1 className="rules-header">The Basic Rules of BlackKing</h1>
              <p className="rules-paragraph">
                        The goal of BlackKing is to beat the dealer's hand without going over 21. Before cards are dealt, players place their bets.

                        Each player starts with two cards that are face up. Face cards are worth 10. Aces are worth 1 or 11; whichever makes a better hand. One of the dealer's two cards is face down until the very end after every player completes his hand.

                        You can ask for another card (hit) or stick with your current hand (stand). You have the option to keep hitting until you're satisfied with your hand, or you go over 21 (bust). If you bust, the dealer wins regardless of the dealer's eventual hand. The dealer must hit until her cards total 17 or higher.
                    </p></div> : <><button onClick={handleShowRules}>Rules of BlackKing</button></> 
            
            }
            </div>
    )
}