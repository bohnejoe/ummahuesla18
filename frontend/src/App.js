import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import './App.css';
var env = require('./env.json');

const AnyReactComponent = ({ text }) => <div className="marker">{text}</div>;

class SimpleMap extends Component {
  constructor(props){
    super(props);
    this.state = { lat: 0, lng: 0 };
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
    console.log(obj.lat, obj.lng);
    this.setState({ lat: obj.lat, lng: obj.lng });
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
            text={'8.2'}
          />
        </GoogleMapReact>
      </div>
    );
  }
}

export default SimpleMap;
