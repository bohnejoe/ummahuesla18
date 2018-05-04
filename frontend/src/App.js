import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import './App.css';
var env = require('./env.json');

class AnyReactComponent extends Component {
  constructor(props) {
    super();
  }

  componentDidUpdate = () => {
    this._draw();
  }

  _draw = () => {
    
  }

  render() {
    return(
      <div className='details'>
        <canvas id='canvas'></canvas>
        <div className={this.props.className}>{this.props.text}</div>
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
    fetch('http://localhost:8080/?lat='+ obj.lat +'&lon='+ obj.lng,
      new Headers({
      'Access-Control-Allow-Origin': '*'
    })).then(result => {
      return result.json();
    }).then(data => {
      console.log(data);
      this.setState({ lat: data.lat, lng: data.lng, text: data.qi, qi: data.qi });
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
            className={ 'marker marker-'+Math.round(this.state.qi*10)}
          />
        </GoogleMapReact>
      </div>
    );
  }
}

export default SimpleMap;
