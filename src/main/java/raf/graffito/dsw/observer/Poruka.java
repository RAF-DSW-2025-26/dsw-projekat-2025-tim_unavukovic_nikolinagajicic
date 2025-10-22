package raf.graffito.dsw.observer;

import java.time.LocalDateTime;

public class Poruka {

    private TipPoruke tipPoruke;
    private String poruka;
    private LocalDateTime timeStamp;

    public Poruka(TipPoruke tipPoruke, String poruka, LocalDateTime timeStamp) {
        this.tipPoruke = tipPoruke;
        this.poruka = poruka;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "["+tipPoruke+"] [ "+ timeStamp+"] " + poruka;
    }

    public TipPoruke getTipPoruke() {
        return tipPoruke;
    }

    public void setTipPoruke(TipPoruke tipPoruke) {
        this.tipPoruke = tipPoruke;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
