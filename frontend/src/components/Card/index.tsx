import axios from "axios";
import { useEffect, useState } from "react";
import { Gatos } from "../../types/gatos";

const Card = () => {
  const [animais, setAnimais] = useState<Gatos[]>([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/gatos")
      .then((response) => setAnimais(response.data))
      .catch((error) => console.error("Erro ao buscar gatos:", error));
  }, []);

  return (
    <div>
      {animais.map((gato) => (
        <div key={gato.id} className="card">
          <div className="card-body">
            <h5 className="card-title">{gato.nome}</h5>
            <img src={gato.url} alt={gato.nome} />
          </div>
        </div>
      ))}
    </div>
  );
};

export default Card;