import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import './App.css';
var env = require('./env.json');

class AnyReactComponent extends Component {
  constructor(props) {
    super();
    this.state = {
      items: []
    }
  }

  componentDidUpdate = () => {
    this._draw();
    this._tick();
  }

  _tick = () => {
    this._draw();
    // this._update();
    requestAnimationFrame(this._tick);
  }

  _draw = () => {
    var canvas = document.getElementById("canvas"),
        ctx = canvas.getContext('2d');

    this.refs.canvas.width = 400;
    this.refs.canvas.height = 400;
    var items = [],
        FPS = 60, // Frames per second
        x = 6;

    var rad_distance = (360/x * 3.1415)/180;

    // Push items to array
    for (var i = 0; i < x; i++) {
      var degrees = i*rad_distance;
      var radius = this.refs.canvas.width*0.3;
      var xx = this.refs.canvas.width/2.0 + radius*Math.cos(degrees);
      var xy = this.refs.canvas.height/2.0 + radius*Math.sin(degrees);
      items.push({
        x: xx,
        y: xy,
        radius: 10,
        vx: Math.floor(Math.random() * 50) - 25,
        vy: Math.floor(Math.random() * 50) - 25
      });
    }

    ctx.clearRect(0,0,canvas.width,canvas.height);
    ctx.globalCompositeOperation = "lighter";

    for (var i = 0, x = items.length; i < x; i++) {
      var s = items[i];

      ctx.fillStyle = "#ff0000";
      ctx.beginPath();
      ctx.arc(s.x, s.y, s.radius, 0, 2 * Math.PI);
      ctx.fill();
      ctx.fillStyle = 'black';
      ctx.stroke();
    }

    ctx.beginPath();
    for (var i = 0, x = items.length; i < x; i++) {
      var starI = items[i];
      ctx.moveTo(starI.x,starI.y);

      ctx.lineTo(this.refs.canvas.height/2.0,this.refs.canvas.width/2.0);
    }
    ctx.lineWidth = 0.35;
    ctx.strokeStyle = 'red';
    ctx.stroke();

    // this.setState({items: items});
  }

  _update = () => {
    var FPS = 60;

    for (var i = 0, x = this.state.items.length; i < x; i++) {
      var s = this.state.items[i];

      s.x += s.vx / FPS;
      s.y += s.vy / FPS;

      // if (s.x < 0 || s.x > this.refs.canvas.width) s.vx = -s.vx;
      // if (s.y < 0 || s.y > this.refs.canvas.height) s.vy = -s.vy;
    }
  }

  distance = ( point1, point2 ) => {
    var xs = 0;
    var ys = 0;

    xs = point2.x - point1.x;
    xs = xs * xs;

    ys = point2.y - point1.y;
    ys = ys * ys;

    return Math.sqrt( xs + ys );
  }

  render() {
    const data = this.props.text === undefined  ? (
      <div className="lds-ripple"><div></div><div></div></div>
    ) : (<div className={this.props.className}>{this.props.text}</div>)
    return(
      <div className='details'>
        <canvas ref='canvas' id='canvas'></canvas>
        {data}
      </div>
    )
  }
};

class SimpleMap extends Component {
  constructor(props){
    super(props);
    this.state = { lat: 0, lng: 0, text: '', qi: 0 };
  }

  static defaultProps = {
    center: {
      lat:  47.677950,
      lng: 9.173238
    },
    location: {
      lat: 45.6779500,
      lng: 9.173238
    },
    zoom: 11
  };

  _onChildClick = (obj) => {
    this.setState({ lat: obj.lat, lng: obj.lng, text: undefined, qi: 0 });

    fetch('http://localhost:8080/?lat='+ obj.lat +'&lng='+ obj.lng,
      new Headers({
      'Access-Control-Allow-Origin': '*'
    })).then(result => {
      return result.json();
    }).then(data => {
      this.setState({ lat: data.lat, lng: data.lng, text: Math.round(data.overallScore*10)/10.0, qi: data.overallScore });
    });
  }

  render() {
    return (
      <div style={{ height: '100vh', width: '100%' }}>
        <GoogleMapReact
          bootstrapURLKeys={{ key: env.google }}
          defaultCenter={this.props.center}
          defaultZoom={this.props.zoom}
          onClick={this._onChildClick}
        >
          <AnyReactComponent
            lat={ this.state.lat }
            lng={ this.state.lng }
            text={ this.state.text }
            className={ 'marker marker-'+Math.round(this.state.qi*10) }
          />
        </GoogleMapReact>
      </div>
    );
  }
}

export default SimpleMap;
