package com.borges.sheets;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SheetsQuickstart {

    private static int incrementAndReturnID(Sheets service, String spreadsheetId, String range) throws IOException {
        ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
        List<List<Object>> values = response.getValues();

        int currentID = 0;

        if (values != null && !values.isEmpty() && values.get(0) != null && !values.get(0).isEmpty()) {
            currentID = Integer.parseInt(values.get(0).get(0).toString());
        }

        // Incrementa o ID em 1
        int newID = currentID + 1;

        // Atualiza a célula do ID na planilha com o novo valor
        ValueRange idUpdate = new ValueRange().setValues(List.of(List.of(newID)));
        service.spreadsheets().values().update(spreadsheetId, range, idUpdate).setValueInputOption("RAW").execute();

        return currentID;
    }


    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";


    //----------------- REDENC
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Recursos nao encontrados: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));


        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES).setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH))).setAccessType("offline").build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("lucasgarciaamorim@gmail.com");
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        final String spreadsheetId = "1_5V7naaPADDvRJuY5SAF5BrelNhuZ558jKXzyrudPJg";
        final String range = "NOTAS APP!A1:B1";
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();

        // METODO GET
      /*  ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            for (List<Object> row : values) {
                if (!row.isEmpty()) {
                    for (Object cellData : row) {
                        System.out.print(cellData + "\t");
                    }
                    System.out.println();
                }

            }

        }*/


        int currentID = incrementAndReturnID(service, spreadsheetId, "A2827");

        //METODO APPEND
        ValueRange body = new ValueRange().setValues(List.of(Arrays.asList(currentID, "CARIMBO DATA/HORA", "EMISSAO", "VENC", "VEN N", "DATA DE CHEGADA", "ATRASO TL", "EM DIAS", "CHAVE DA NFE", "NOTA", "PEDIDO", "LOJA", "MARCA", "NÃO")));

        AppendValuesResponse result = service.spreadsheets().values().append(spreadsheetId, "NOTAS APP", body).setValueInputOption("RAW").setInsertDataOption("INSERT_ROWS").execute();

        System.out.println("Dados adicionados: " + result.getUpdates().getUpdatedCells() + " células adicionadas.");
    }

}




