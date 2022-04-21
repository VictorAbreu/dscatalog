import { render, screen } from "@testing-library/react";
import ButtonIcon from "..";


test('ButtonIcon deveria renderizar o botão com o texto informado', () => {

    const text = "Fazer login";

    render(
        <ButtonIcon text={text} />
    );


    expect(screen.getByText(text)).toBeInTheDocument();
    expect(screen.getByTestId("arrow")).toBeInTheDocument();

});