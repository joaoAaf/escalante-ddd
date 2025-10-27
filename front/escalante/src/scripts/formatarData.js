export const formatarData = (dataString) => {
    if (!dataString) return "-"
    try {
        const [ano, mes, dia] = dataString.split("-")
        return `${dia}/${mes}/${ano}`
    } catch (error) {
        console.error("Erro ao formatar data:", dataString, error)
        return "-"
    }
}