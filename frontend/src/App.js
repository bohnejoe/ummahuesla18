import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import logo from './logo.svg';
import './App.css';
import './marker1.css';
import './marker2.css';
import './marker3.css';
import './marker4.css';
import './marker5.css';
import './marker6.css';

var env = require('./env.json');

class AnyReactComponent extends Component {
  constructor(props) {
    super();
    this.state = {
      items: [],
      indicators: []
    }
  }

  componentDidUpdate = () => {
    // this._generatePositions();
  }

  _generatePositions = () => {
    var items = [];

    var amount = this.props.indicators ? this.props.indicators.length : 0;
    var rad_distance = (((360/amount) +15 ) * 3.1415)/180;
    var width = 400;
    // Push items to array
    for (var i = 0; i < amount; i++) {
      var item = this.props.indicators[i];
      var degrees = i*rad_distance;
      var radius = width*0.2;
      var xx = width/2.0 + radius*Math.cos(degrees) - 25;
      var xy = width/2.0 + radius*Math.sin(degrees) - 25;
      items.push({
        x: xx,
        y: xy,
        radius: 10,
        vx: Math.floor(Math.random() * 50) - 25,
        vy: Math.floor(Math.random() * 50) - 25,
        item: item
      });
    }
    return items;
  }

  render() {
    var items = this._generatePositions();

    const indicators = items.map((item,index)=> {
      const qi = Math.min(Math.round(item.item.score*10)/10,9.9);
      const markerClassname = 'marker-small marker marker-'+ (index%6+1) +'-'+ qi*10;
      const divStyle = {
        position:'absolute',
        left: item.x,
        top: item.y
      };
      return (<div className={markerClassname} style={divStyle}>{qi}<span ref='name'>{item.item.name}</span></div>)
    });

    const data = this.props.text === undefined  ? (
      <div className="lds-ripple"><div></div><div></div></div>
    ) : (<div className={this.props.className}>{this.props.text}</div>)
    return(
      <div className='details'>
        {indicators}
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
      lat:  48.135125,
      lng: 11.581980
    },
    location: {
      lat: 48.135125,
      lng: 11.581980
    },
    zoom: 11
  };

  _onChildClick = (obj) => {
    this.setState({ lat: obj.lat, lng: obj.lng, text: undefined, qi: 0, indicators: []});

    fetch('http://localhost:8080/?lat='+ obj.lat +'&lng='+ obj.lng,
      new Headers({
      'Access-Control-Allow-Origin': '*'
    })).then(result => {
      return result.json();
    }).then(data => {
      this.setState({
        lat: data.lat,
        lng: data.lng,
        text: Math.round(data.overallScore*10)/10.0,
        qi: data.overallScore,
        indicators: data.inidicators
      });
    });
  }

  render() {
    return (
      <div style={{ height: '100vh', width: '100%' }}>
        <img src={logo} className="logo" alt="logo" />
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
            indicators={ this.state.indicators }
            className={ 'marker marker-'+Math.round(this.state.qi*10) }
          />
        </GoogleMapReact>
      </div>
    );
  }
}

export default SimpleMap;
