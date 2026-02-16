import { useState } from 'react';
import { createUser } from '../api/api';

export default function UserForm({ onCreated, onCancel }) {
  const [form, setForm] = useState({ username: '', email: '', password: '' });
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      await createUser(form);
      setForm({ username: '', email: '', password: '' });
      onCreated();
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-3 rounded-lg border border-gray-200 bg-gray-50 p-4">
      <h3 className="text-lg font-semibold text-gray-800">Nuovo Utente</h3>
      {error && <p className="text-sm text-red-600">{error}</p>}
      <input
        type="text"
        placeholder="Username"
        required
        value={form.username}
        onChange={(e) => setForm({ ...form, username: e.target.value })}
        className="w-full rounded border border-gray-300 px-3 py-2 focus:border-indigo-500 focus:ring-1 focus:ring-indigo-200 focus:outline-none"
      />
      <input
        type="email"
        placeholder="Email"
        required
        value={form.email}
        onChange={(e) => setForm({ ...form, email: e.target.value })}
        className="w-full rounded border border-gray-300 px-3 py-2 focus:border-indigo-500 focus:ring-1 focus:ring-indigo-200 focus:outline-none"
      />
      <input
        type="password"
        placeholder="Password"
        required
        value={form.password}
        onChange={(e) => setForm({ ...form, password: e.target.value })}
        className="w-full rounded border border-gray-300 px-3 py-2 focus:border-indigo-500 focus:ring-1 focus:ring-indigo-200 focus:outline-none"
      />
      <div className="flex gap-2">
        <button
          type="submit"
          className="rounded bg-indigo-600 px-4 py-2 text-white hover:bg-indigo-700"
        >
          Crea
        </button>
        <button
          type="button"
          onClick={onCancel}
          className="rounded bg-gray-300 px-4 py-2 text-gray-700 hover:bg-gray-400"
        >
          Annulla
        </button>
      </div>
    </form>
  );
}
