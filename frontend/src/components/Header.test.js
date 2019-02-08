import React from 'react';
import {render, cleanup } from "react-testing-library";
import "jest-dom/extend-expect";
import Header from "./Header";

afterEach(cleanup);

 it("renders", () => {
  const { asFragment } = render(<Header text="Sentiment" />);
   expect(asFragment()).toMatchSnapshot();
 });

it("inserts text in h1", () => {
  const { getByTestId, getByText } = render(<Header text="Sentiment" />);

expect(getByTestId('h1tag')).toHaveTextContent("Sentiment");
expect(getByText('Sentiment')).toHaveClass("Header");
});
