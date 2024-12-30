package com.hackathon.inditex.DTO.mappers;

public interface IMapper <I, O>{

    public O map(I in);
}