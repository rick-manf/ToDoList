import { useEffect, useState } from 'react';
import { getUsers } from '../api/api';

export default function UserSelect({ selectedUser, onSelect, refreshKey }) {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    getUsers().then(setUsers).catch(console.error);
  }, [refreshKey]);

  return (
    <select
      value={selectedUser?.id ?? ''}
      onChange={(e) => {
        const user = users.find((u) => u.id === Number(e.target.value));
        onSelect(user || null);
      }}
      className="w-full rounded-lg border border-gray-300 bg-white px-4 py-2 text-gray-700 shadow-sm focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 focus:outline-none"
    >
      <option value="">-- Seleziona utente --</option>
      {users.map((u) => (
        <option key={u.id} value={u.id}>
          {u.username} ({u.email})
        </option>
      ))}
    </select>
  );
}
