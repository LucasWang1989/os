export function formatDateTime(str) {
    const iso = `${str.slice(0,4)}-${str.slice(4,6)}-${str.slice(6,8)}T${str.slice(8,10)}:${str.slice(10,12)}:${str.slice(12,14)}`;
    return new Date(iso).toLocaleString();
}

