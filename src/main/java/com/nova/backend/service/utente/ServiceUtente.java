package com.nova.backend.service.utente;

import com.nova.backend.model.utente.Ruolo;
import com.nova.backend.model.utente.TipoCliente;
import com.nova.backend.model.utente.Utente;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceUtente {
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public ServiceUtente(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /*
      ---------------------------------------------
      MODIFICARE IL TUTTO CON I DTO
      ---------------------------------------------
     */

    //REGISTRAZIONE UTENTE
    public Object registrazioneUtente(DTO req) {
        //VERIFICA SE L'UTENTE ESISTE GIA TRAMITE EMAIL
        if(this.utenteRepository.existsByEmail(req.getEmail())){
            return return new DTO("Email already in use", 401, System.currentTimeMillis());
        }
        //INSERIMENTO DEI DATI
        //modificare con il DTO
        Utente utente = new Utente();
        utente.setEmail(req.getEmail());
        utente.setNome(req.getNome());
        utente.setCognome(req.getCognome());
        //VERIFICHE
        //------------------------------------------------------------------------------
        if(ruoloValido(req.getRuolo())){
            utente.setRuolo(req.getRuolo());
        }else {return new DTO("Email already in use", 401, System.currentTimeMillis());}
        if(tipoValido(req.getTipoCliente())){
            utente.setTipoCliente(req.getTipoCliente());
        }else {return new DTO("Email already in use", 401, System.currentTimeMillis());}
        //------------------------------------------------------------------------------
        utente.setPassword(passwordEncoder.encode(req.getPassword()));
        utente.setAttivo(true);
        utenteRepository.save(utente);

        //modificare con il DTO
        return new DTO("User registered successfully", userSave);
    }

    //VERIFICA SE QUELLO CHE VIENE INSERITO CORRISPONDE ALL'ENUM
    //-----------------------------------------------------------
    public boolean ruoloValido(Object ruolo) {
        try {
            Ruolo.valueOf(ruolo.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean tipoValido(Object tipo) {
        try {
            TipoCliente.valueOf(tipo.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    //-----------------------------------------------------------


}
