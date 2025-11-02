export function obterUltimoId(list = [], idKey = 'id') {
    return (list || []).reduce((max, item) => Math.max(max, Number(item?.[idKey] ?? 0)), 0)
}

export function obterProximoId(list = [], idKey = 'id') {
    return obterUltimoId(list, idKey) + 1
}

export function inserirIds(list = [], idKey = 'id') {
    const items = list || []
    let maxId = obterUltimoId(items, idKey)
    let next = maxId + 1
    return items.map(item => item?.[idKey] ? item : { ...item, [idKey]: next++ })
}
