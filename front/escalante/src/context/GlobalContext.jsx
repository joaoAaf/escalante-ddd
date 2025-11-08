import { createContext } from 'react'
import { useLocalStorage } from '../hooks/useLocalStorage'

export const GlobalContext = createContext()

export const GlobalContextProvider = ({ children }) => {
    
    const [escala, setEscala] = useLocalStorage('escala')
    const [militares, setMilitares] = useLocalStorage('militares')

    return (
        <GlobalContext.Provider value={{ escala, setEscala, militares, setMilitares }}>
            {children}
        </GlobalContext.Provider>
    )
}