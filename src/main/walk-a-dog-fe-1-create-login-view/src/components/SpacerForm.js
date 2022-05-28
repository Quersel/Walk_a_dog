import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./HeroSection.css";
import WyborTrenera from "./WyborTrenera";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";

const SpacerForm = () => {
  const [startDate, setStartDate] = useState(null);

  //  Zmiana koloru godziny
  //   let handleColor = (time) => {
  //     return time.getHours() > 12 ? "text-success" : "text-error";
  //   };

  return (
    <Stack className="hero-container" spacing={2}>
      <h3>Zaplanuj spacer!</h3>
      <DatePicker
        className="date-picker"
        selected={startDate}
        onChange={(date) => setStartDate(date)}
        showTimeSelect
        timeIntervals={60}
        timeFormat="HH:mm"
        dateFormat="dd/MM/yyyy HH:mm"
        placeholderText="Podaj date"
      />
      <WyborTrenera />
      <Button className="btn" variant="contained" color="error">
        Anuluj
      </Button>
      <Button className="btn" variant="contained" color="success">
        Zamawiam spacer
      </Button>
    </Stack>
  );
};

export default SpacerForm;
