package com.conversor;

import com.conversor.dto.ConvertionRatesDto;
import com.conversor.fetchapi.Desserealization;
import com.conversor.fetchapi.FetchApi;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Menu {

  public void openMenu() throws IOException, InterruptedException {
    boolean isMenuOpen = true;

    Scanner scanMenu = new Scanner(System.in);

    while (isMenuOpen) {
      System.out.println("Seja bem-vindo/a ao Conversor de moedas\n");
      System.out.println("1 - Dólar >>> Peso argentino");
      System.out.println("2 - Peso argentino >> Dólar");
      System.out.println("3 - Dólar >>> Real Brasileiro");
      System.out.println("4 - Real Brasileiro >>> Dólar");
      System.out.println("5 - Dólar >>> Peso Colombiano");
      System.out.println("6 - Peso colombiano >>> Dólar");
      System.out.println("7 - Sair\n");
      System.out.println("Escolha uma opção para conversão.");
      System.out.println("#####################################################");
      int menuOption = scanMenu.nextInt();

      switch (menuOption) {
        case 1:
          exchangeMenu(scanMenu, "USD", "ARS");
          break;
        case 2:
          exchangeMenu(scanMenu, "ARS", "USD");
          break;
        case 3:
          exchangeMenu(scanMenu, "USD", "BRL");
          break;
        case 4:
          exchangeMenu(scanMenu, "BRL", "USD");
          break;
        case 5:
          exchangeMenu(scanMenu, "USD", "COP");
          break;
        case 6:
          exchangeMenu(scanMenu, "COP", "USD");
          break;
        case 7:
          isMenuOpen = false;
          break;
        default:
          System.out.println("Opção Inválida.");
      }

    }
    scanMenu.close();
  }

  private void exchangeMenu(Scanner scanMenu, String currency, String against)
      throws IOException, InterruptedException {
    System.out.println("Digite o valor que deseja converter.");
    double valueToExchange = scanMenu.nextDouble();

    FetchApi api = new FetchApi();
    HttpResponse<String> response = api
        .getExchangeRates(currency);

    Desserealization desserealization = new Desserealization();
    ConvertionRatesDto convertionRates = desserealization.fromJson(response.body());

    double againstValue = convertionRates.getConversionRates().get(against) * valueToExchange;

    System.out.println("O valor de " + valueToExchange + " [" + currency + "]"
        + " corresponde a ao valor final de " + String.format("%.2f", againstValue)
        + " em [" + against + "]\n\n");
    System.out.println("#####################################################");
  }
}
