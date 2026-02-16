import { useState } from 'react';
import { createTask } from '../api/api';

export default function TaskForm({ userId, onCreated }) {
  const [form, setForm] = useState({ title: '', description: '', dueDate: '' });
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      await createTask({
        title: form.title,
        description: form.description || null,
        dueDate: form.dueDate || null,
        completed: false,
        userId,
      });
      setForm({ title: '', description: '', dueDate: '' });
      onCreated();
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-2 sm:flex-row sm:items-end">
      <input
        type="text"
        placeholder="Nuovo task..."
        required
        value={form.title}
        onChange={(e) => setForm({ ...form, title: e.target.value })}
        className="flex-1 rounded-lg border border-gray-300 px-3 py-2 focus:border-indigo-500 focus:ring-1 focus:ring-indigo-200 focus:outline-none"
      />
      <input
        type="text"
        placeholder="Descrizione (opzionale)"
        value={form.description}
        onChange={(e) => setForm({ ...form, description: e.target.value })}
        className="rounded-lg border border-gray-300 px-3 py-2 sm:w-48 focus:border-indigo-500 focus:ring-1 focus:ring-indigo-200 focus:outline-none"
      />
      <input
        type="date"
        value={form.dueDate}
        onChange={(e) => setForm({ ...form, dueDate: e.target.value })}
        className="rounded-lg border border-gray-300 px-3 py-2 focus:border-indigo-500 focus:ring-1 focus:ring-indigo-200 focus:outline-none"
      />
      <button
        type="submit"
        className="rounded-lg bg-indigo-600 px-4 py-2 text-white hover:bg-indigo-700"
      >
        Aggiungi
      </button>
      {error && <p className="text-sm text-red-600">{error}</p>}
    </form>
  );
}
