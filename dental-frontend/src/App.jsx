import { useState } from 'react';
import axios from 'axios';

function App() {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);
    setMessage('');
    setIsError(false);

    try {
      const response = await axios.post('/auth/login', formData);
      const token = response?.data?.data?.jwtToken;

      if (token) {
        localStorage.setItem('jwtToken', token);
      }

      setMessage(response?.data?.message || "Muvaffaqiyatli kirdingiz");
      setIsError(false);
    } catch (error) {
      const apiMessage =
        error?.response?.data?.message ||
        error?.response?.data?.error ||
        'Login xato bo‘ldi';

      setMessage(apiMessage);
      setIsError(true);
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="page">
      <section className="card">
        <h1>Dental Clinic</h1>
        <p>Xush kelibsiz</p>

        <form onSubmit={handleSubmit}>
          <label htmlFor="email">Email</label>
          <input
            id="email"
            name="email"
            type="email"
            placeholder="example@mail.com"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <label htmlFor="password">Parol</label>
          <input
            id="password"
            name="password"
            type="password"
            placeholder="********"
            value={formData.password}
            onChange={handleChange}
            minLength={8}
            maxLength={8}
            required
          />

          <button type="submit" disabled={loading}>
            {loading ? 'Yuborilmoqda...' : 'Kirish'}
          </button>
        </form>

        {message && (
          <p className={isError ? 'message error' : 'message success'}>{message}</p>
        )}
      </section>
    </main>
  );
}

export default App;
