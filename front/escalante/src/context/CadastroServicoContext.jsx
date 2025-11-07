import { createContext, useState } from 'react'

export const CadastroServicoContext = createContext()

export const CadastroServicoContextProvider = ({ children }) => {
    
    const [statusModal, setStatusModal] = useState(false)

    return (
        <CadastroServicoContext.Provider value={{ statusModal, setStatusModal }}>
            {children}
        </CadastroServicoContext.Provider>
    )
}
