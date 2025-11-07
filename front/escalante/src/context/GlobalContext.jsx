import { createContext, useState } from 'react'

export const GlobalContext = createContext()

export const GlobalContextProvider = ({ children }) => {
    
    const [escala, setEscala] = useState(null)
    const [militares, setMilitares] = useState(null)

    return (
        <GlobalContext.Provider value={{ escala, setEscala, militares, setMilitares }}>
            {children}
        </GlobalContext.Provider>
    )
}