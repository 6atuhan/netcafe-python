from tkinter import *

import requests


url = "http://api.openweathermap.org/data/2.5/weather"
api_key = "8aa0b146e578045b0c6d35e4b88c1f4e"


def getWeather(city):
    params= {"q":city,"appid":api_key,"lang":"tr"}
    data = requests.get(url,params=params).json()
    if data:
        city=data["name"].capitalize()
        country = data["sys"]["country"]
        temp= int(data["main"]["temp"]-273.15)
        print(data)
        return  (city,country,temp)

def paraDegis(temp):
    dosyaUrl="C:/Users/alt/eclipse-workspace/HFTTF_NetCafe/dosya.txt"
    dosya=open(dosyaUrl,"r")
    derece=int(temp)
    tempFiyat =dosya.read()
    tempFiyat=float(tempFiyat)
    yeniFiyat=tempFiyat
    yeniFiyat=float(yeniFiyat)
    dosya = open(dosyaUrl, "w")
    if (temp < 0):

        yeniFiyat = yeniFiyat-(yeniFiyat*0.5)
    elif (temp > 0):
        yeniFiyat = yeniFiyat-(yeniFiyat*0.1)
    dosya.write("{} = >Hava durumu endeksli yeni fiyat :{}TL".format(tempFiyat,yeniFiyat))



def main():
    city = cityEntry.get()
    weather= getWeather(city)
    if weather:
        locationLabel["text"]="{},{}".format(weather[0],weather[1])
        tempLabel["text"] = "{}C".format(weather[2])
        paraDegis(weather[2])



app = Tk()
app.geometry("300x400")
app.title("Para Güncelle")


cityEntry = Entry(app,justify="center")
cityEntry.pack(fill=BOTH, ipady=10,padx=18,pady=5)
cityEntry.focus()

searchButton = Button(app,text="arama",font=("Arial",15),command=main)
searchButton.pack(fill=BOTH,ipady=10,padx=20)

locationLabel=Label(app,font=("Arial",40))
locationLabel.pack()

tempLabel=Label(app,font=("Arial",15,"bold"))
tempLabel.pack()

app.mainloop()