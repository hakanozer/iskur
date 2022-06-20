import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { userRegister } from './Services';


function Register() {



    // sayfa geçiş hareketleri için kullanılan bir yonmtmdir
    const navigate = useNavigate()

    const [name, setName] = useState('')
    const [surname, setSurname] = useState('')
    const [phone, setPhone] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [city, setCity] = useState('')
    const [registerError, setRegisterError] = useState(false) //kullanıcı giriş yapınca true yapıcaz
    const [registerMessage, setRegisterMessage] = useState('')
    //arrow function

    const fncSend = (evt: React.FormEvent) => {
        evt.preventDefault();

        userRegister(name, surname, phone, email, password).then(res => {

            const user = res.data.user[0]  //dönen cevap
            
            const durum = user.durum
            const mesaj = user.mesaj  //servisten gelen cevapla doldu mesaj
            setRegisterMessage(mesaj)
            if (durum) {
                alert(mesaj)
                navigate('/')
            } else {
                // alert(mesaj)
                setRegisterError(true)
            }
        })


        console.log("fncSend called");
        console.log(city);
    };


    //useEffect
    useEffect(() => {
        //buraa retunun içindeki html yaratıldıktan sonra çalışır 
        console.log('useeffect call-1')
    }, [])

    useEffect(() => {

        if (name.length > 2) {
            //birden fazla olur. hangi sırayla yazdıysak yukardan aşa gelir.
            console.log('useeffect call-2', name)
        }



    }, [name]) //kçşeli parantez iiçinde state ürünlerdinden birini atarsak name yazesak sürekli çalışır

    //array
    const arr: string[] = ['İstanbul', 'izmir', "Antalya", "Muğla", "Balıkesir", "Çeşme"] //şuan bir array list oluşturduk //any içine her şey atilabilir. biz bunu her şey yollamasınu istemiyoruz. string[] dizi olduğunu belirttik.İnterface türü de olur
    //gecikme ihtimali ve daha sonradan değişme ihtimali olanlarda state kullanılır. Daha sonradan dolması için

    //map bir diziyi tüketmek için kullanılan bir araçtır. döngüye sokar for eaach java tarafınfa

    return (
        <>
            <div className="row">
                <div className="col-sm-4"></div>
                <div className="col-sm-4">
                    <h1>User Register</h1>
                    <div style={{ display: registerError === true ? 'block' : 'none' }} className="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>Error!</strong> {registerMessage}
                        <button type="button" className="btn-close" aria-label="Close" onClick={(evt) => setRegisterError(false)}  ></button>
                    </div>
                    <form onSubmit={fncSend} >
                        <div className="mt-3">
                            <input onChange={(evt) => { setName(evt.target.value) }} type="text" className="form-control" placeholder="Name" />
                        </div>
                        <div className="mt-3">
                            <input onChange={(evt) => { setSurname(evt.target.value) }} type="text" className="form-control" placeholder="Surname" />
                        </div>
                        <div className="mt-3">
                            <input onChange={(evt) => { setPhone(evt.target.value) }} type="tel" className="form-control" placeholder="Phone" />
                        </div>
                        <div className="mt-3">
                            <input onChange={(evt) => { setEmail(evt.target.value) }} type="email" className="form-control" placeholder="e-Mail" />
                        </div>
                        <div className="mt-3">
                            <input onChange={(evt) => { setPassword(evt.target.value) }} type="password" className="form-control" placeholder="Password" />
                        </div>
                        {/* 
                        <div className="mt-3">
                            <select required onChange={(evt) => { setCity(evt.target.value) }} className="form-select" aria-label="Default select example">
                                <option value="" >Select City</option>
                                {arr.map((item, index) =>
                                    <option key={index} value={item}>{item}</option>
                                )}


                            </select>
                        </div>*/}
                        <div className='mt-3'>
                            <a onClick={(evt) => navigate("/")} role="button" className='btn btn-danger'>Back</a>
                            <button className='btn btn-success' type='submit'>Send</button>

                        </div>

                    </form>

                </div>
                <div className="col-sm-4"></div>
            </div>


        </>
    )
}

export default Register